package com.sing.dubbo.provider;


import com.sing.dubbo.api.DemoApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 针对Consumer端调用的配置
 * 1.timeout方法调用的超时时间
 * 2.retries:失败重试次数，缺省是2
 * 3.loadbalance:负载均衡算法，缺省是随机random+权重。还可以配置轮询roundrobin、最不活跃优先leastactive和一致性哈希 consistenthash等
 * 4.actives:消费者端的最大并发调用限制，即当consumer对一个服务的并发调用到上限后，新调用会阻塞直到超时，
 * 在方法上配置dubbo:method则针对该方法进行并发限制，在接口上配置dubbo:service,则针对该服务进行并发限制
 * 5.executes：服务提供方可以使用的最大线程数
 * 针对Provider端的配置
 * 1.threads:服务线程池大小
 * 2.excutes:一个服务提供者并行执行请求上限
 */
@DubboService
//(version = "1.0.0",timeout = 10000,interfaceClass = IDemoService.class,
// loadbalance="roundrobin")
public class DemoApiImpl implements DemoApi {

    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());

        return "Hello " + name + "," + RpcContext.getContext().getLocalPort();
    }
}
