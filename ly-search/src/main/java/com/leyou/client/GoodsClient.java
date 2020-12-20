package com.leyou.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "item-service" ,decode404 = true)
public interface GoodsClient extends GoodsApi {
}
