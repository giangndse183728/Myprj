/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.sp24.t2s1.weapon;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nguye
 */
public class Cart {
    private Map<String, Weapon> cart;

    public Cart() {
    }

    public Cart(Map<String, Weapon> cart) {
        this.cart = cart;
    }

    public Map<String, Weapon> getCart() {
        return cart;
    }

    public void setCart(Map<String, Weapon> cart) {
        this.cart = cart;
    }


    
    public boolean add(Weapon weapon) {
        boolean check = false;
        if ( this.cart == null) {
            this.cart = new HashMap<>();
            
        }
        if (this.cart.containsKey(weapon.getId())) {
            int currentQuantity = this.cart.get(weapon.getId()).getQuantity();
            weapon.setQuantity(currentQuantity+ weapon.getQuantity());
        }
        this.cart.put(weapon.getId(), weapon);
        check = true;
        return check;
    }
    
    public boolean remove(String weaponID) {
        boolean check = false;
        if ( this.cart.containsKey(weaponID)) {
            this.cart.remove(weaponID);
            check = true;
        }
        return check;
    }
    
    public boolean edit (String id, int quantity) {
         boolean check = false;
         if (this.cart != null) {
         if ( this.cart.containsKey(id)) {
             Weapon weapon = this.cart.get(id);
             weapon.setQuantity(quantity);
             this.cart.replace(id, weapon);
             check = true;
         }
        
    }
    
         return check;
}
}
