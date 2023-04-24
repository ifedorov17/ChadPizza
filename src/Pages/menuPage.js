import React, {useContext, useEffect, useState} from 'react';
import PizzaList from "../Components/pizzaList";
import {Context} from "../index";
import CRUDService from "../CRUDService";
import {PIZZAS, setPizzas, ShopContext} from "../Context/allDeliveryContext";
import {Toast, ToastContainer} from "react-bootstrap";

const MenuPage = () => {
    const {pizza} = useContext(Context);
    useEffect(() => {
        CRUDService.getAllPizzas().then((res) => {
            pizza.setPizzaProps(res.data);
            setPizzas();
            console.log(res);
            console.log(pizza.getPizzaProps())
        }
        )
    }, [pizza])
    return (
        <div>
            <PizzaList/>

        </div>
    );
};

export default MenuPage;