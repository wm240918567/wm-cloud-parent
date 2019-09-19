package com.wmcloud.broker.strategy;

import com.wmcloud.broker.dao.MessageRepository;
import com.wmcloud.broker.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.wmframework.result.Resp;
import org.wmframework.result.RespConst;

import java.util.List;

@Component("HTTP_JSON")
@Slf4j
public class HttpJsonMesasgeSendStrategy implements MesasgeSendStrategy {

    private static int num = 0;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MessageRepository repository;

    @Override
    public boolean send(Message message) {
        List<ServiceInstance> instances = discoveryClient.getInstances(message.getToSys());
        for (; num < instances.size(); num++) {
            try {
                ServiceInstance instance = instances.get(num);
                String host = instance.getHost();
                int port = instance.getPort();
                String url = host + port + message.getPath();
                if (message.getMethod().equals(HttpMethod.POST)) {
                    Resp resp = restTemplate.postForObject(url, message.getBody(), Resp.class);
                    if (null != resp) {
                        if (resp.getStatus().equals(RespConst.OK.name())) {
                            log.info("消息发送并返回处理成功");
                            message.setStatus(3);
                            repository.save(message);
                        } else {
                            log.error("消息发送失败");
                            //TODO 重发或失败操作
                        }
                    }
                }
            } finally {
                if (num == instances.size() - 1) {
                    num = 0;
                }
            }

        }
        return true;
    }
}
