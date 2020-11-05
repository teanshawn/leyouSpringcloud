package com.leyou.client;

import com.leyou.LySearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SocketUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchService.class)
public class CategoryClientTest {

    @Autowired
    CategoryClient categoryClient;

    @Test
    public void test(){
        List<String> nameByIds = categoryClient.queryNameByIds(Arrays.asList(1l, 2l, 3l));
        nameByIds.forEach(System.out::println);
    }
}