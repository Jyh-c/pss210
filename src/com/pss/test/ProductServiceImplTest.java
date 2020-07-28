package com.pss.test;

import com.pss.po.Product;
import com.pss.service.IProductService;
import com.pss.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/6/29 1:10
 */
public class ProductServiceImplTest {

        IProductService ips = new ProductServiceImpl();
        @Test
        public void testQueryAll() {
            List<Product> pList = ips.queryAll();
            Assert.assertEquals(3, pList.size());
        }

        @Test
        public void testAdd() {
            Product p = new Product(0,"华为P40",6800,100,1);
            ips.add(p);
        }

        @Test
        public void testUpdate() {
            Product p = new Product(4,"华为P30",4500,100,1);
            ips.update(p);
        }

        @Test
        public void testDel() {
            ips.del(4);
        }

        @Test
        public void testQueryById() {
            Product p = ips.queryById(4);
            System.out.println(p);
        }

        @Test
        public void testQueryByName() {
            List<Product> pList = ips.queryByName("为", 1, 5);
            for(Product p:pList) {
                System.out.println(p);
            }
        }

        @Test
        public void testFindTotalNum() {
            int total = ips.findTotalNum("为");
            Assert.assertEquals(2,total);
        }
}