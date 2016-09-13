package com.epam.cache;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;

import java.util.concurrent.ExecutionException;

/**
 * @author Andrei_Yakushin
 * @since 9/13/2016 2:30 PM
 */
public class Task1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RedisClient client = new RedisClient("ECSC0010558E.epam.com");
        StatefulRedisConnection<String, String> connection = client.connect();

        RedisAsyncCommands<String, String> commands = connection.async();
        RedisFuture<String> set = commands.set("1", "2");
        set.get();

        RedisFuture<String> get = commands.get("1");
        String s = get.get();
        System.out.println(s);
    }
}
