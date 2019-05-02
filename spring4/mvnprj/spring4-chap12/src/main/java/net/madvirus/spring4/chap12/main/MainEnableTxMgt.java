package net.madvirus.spring4.chap12.main;

import net.madvirus.spring4.chap12.store.service.PlaceOrderService;
import net.madvirus.spring4.chap12.store.service.PurchaseOrderRequest;
import net.madvirus.spring4.chap12.store.service.PurchaseOrderResult;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainEnableTxMgt {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

		PlaceOrderService orderService = ctx.getBean(PlaceOrderService.class);
		PurchaseOrderRequest orderRequest = new PurchaseOrderRequest();
		orderRequest.setItemId(1);
		orderRequest.setAddress("주소");

		PurchaseOrderResult orderResult = orderService.order(orderRequest);
		System.out.println(orderResult.getOrder().getId());

		ctx.close();
	}

}
