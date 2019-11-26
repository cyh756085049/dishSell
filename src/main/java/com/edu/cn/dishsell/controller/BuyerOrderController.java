package com.edu.cn.dishsell.controller;

import com.edu.cn.dishsell.converter.OrderDTOToOrderFormConverter;
import com.edu.cn.dishsell.dto.OrderDTO;
import com.edu.cn.dishsell.enums.ResultEnum;
import com.edu.cn.dishsell.exception.SellException;
import com.edu.cn.dishsell.form.OrderForm;
import com.edu.cn.dishsell.service.BuyerService;
import com.edu.cn.dishsell.service.OrderService;
import com.edu.cn.dishsell.utils.ResultVOUtil;
import com.edu.cn.dishsell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cyh
 * @Date: 2019-11-12 18:02
 * @Description: 买家端订单
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        OrderDTO orderDTO = OrderDTOToOrderFormConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10")int size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param orderId
     * @param openid
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("orderId") String orderId,
                                     @RequestParam("openid") String openid) {
        // TODO 不安全的做法，需要改进
//        OrderDTO orderDTO = orderService.findOne(orderId);

        // 完成改进
        OrderDTO orderDTO = buyerService.findOrderOne(orderId, openid);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param orderId
     * @param openid
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("orderId") String orderId,
                           @RequestParam("openid") String openid) {
        // TODO 不安全的做法，改进
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        orderService.cancel(orderDTO);

        // 完成改进
        OrderDTO orderDTO = buyerService.cancelOrder(orderId, openid);
        return ResultVOUtil.success();
    }
}
