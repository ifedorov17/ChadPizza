import React, {createContext, useContext, useState} from "react";
import {Context} from "../index";
import axios from "axios";
import CRUDService from "../CRUDService";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
export const ShopContext = createContext(null);

const getDefaultCart = () => {
    let cart = {}
    for (let i = 1; i < PIZZAS.length + 1; i++)
    {
        cart[i] = 0
    }
    return cart;
}

export let PIZZAS = [             { id : 1, name : "Маргарита", price : "600", pictureUrl : "https://lh3.googleusercontent.com/-F7-f2RyixFJ_0-MIGehlz7lp08CkWuy7Y64qDx8zcSrAyHA_uWVnJx1XOVAHg_qoFD7fW34aWScKlOz7tlHx8LeBxDoB64vaZ6LCKKMAPPnr8-QTpPpQVVK-xGPWFZomSVkVZXW"},
    { id : 2, name : "4 Сыра", price : "800", pictureUrl : "https://e2.edimdoma.ru/data/recipes/0010/4988/104988-ed4_wide.jpg?1628783796" },
    { id : 3, name : "Пеперони", price : "800", pictureUrl : "https://s1.eda.ru/StaticContent/Photos/120131085053/171027192707/p_O.jpg"},
    { id : 4, name : "От шефа", price : "900", pictureUrl : "https://irecommend.ru/sites/default/files/product-images/1502324/9GTaqO1eKqLg56gggJBv9A.jpg"},
    { id : 5, name : "От ", price : "90", pictureUrl : "https://irecommend.ru/sites/default/files/product-images/1502324/9GTaqO1eKqLg56gggJBv9A.jpg"},];

export const setPizzas = () => {
    CRUDService.getAllPizzas().then((res) =>{
        PIZZAS = res.data;
        console.log(PIZZAS);
    })
}
export const Toasty = (idx, message) => {
    if(idx === 1)
    {
        toast.success(message, {position: "bottom-left",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",});
    }
    else if (idx === 404)
    {
        toast.error("Что то пошло не так!", {position: "bottom-left",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",})
    }
}

export const ShopContextProvider = (props) => {
    const [basketItems, setBasketItems] = useState(getDefaultCart());


    const addToCart = (pizzaId) => {
        setBasketItems((prev) => ({...prev, [pizzaId] : prev[pizzaId] + 1}))
    };
    const removeFromCart = (pizzaId) => {
        setBasketItems((prev) => ({...prev, [pizzaId] : prev[pizzaId] - 1}))
    };
    const clearCart = () => {
        setBasketItems(getDefaultCart());
    };
    const getBasketTotal = () => {
        let total = 0;
        for(const pizza in basketItems){
            if(basketItems[pizza] > 0) {
                let itemInfo = PIZZAS.find((el) => el.id === Number(pizza));
                total += basketItems[pizza] * itemInfo.price

            }

        }
        return total;
    };

    const getBasketAmount = () =>
    {
        let counter = 0;
        for(const pizza in basketItems) {
            if(basketItems[pizza] > 0)
            {
                counter += basketItems[pizza];
            }
        }
        return Math.floor(counter);
    }

    const contextValue = {basketItems, addToCart, removeFromCart, clearCart, getBasketTotal, getBasketAmount};

    return <ShopContext.Provider
        value={contextValue} v>{props.children}</ShopContext.Provider>
}