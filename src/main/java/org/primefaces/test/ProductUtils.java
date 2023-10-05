/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.test;

/**
 *
 * @author NCI Admin
 */
public class ProductUtils {
    public enum InventoryStatus {
        OUTOFSTOCK("out of stock"),
        LOWSTOCK("low stock"),
        INSTOCK("in stock");

        private final String text;

        InventoryStatus(String text) {
            this.text = text;
        }
        
        public String getText() {
            return text;
        }

    }
}
