import React, {useContext, useState} from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';
import {ShopContext} from "../Context/allDeliveryContext";
import PizzaItem from "./pizzaItem";
import {Context} from "../index";
import {clear} from "@testing-library/user-event/dist/clear";
import {useNavigate} from "react-router-dom";
import {ORDER_ROUTE, PIZZA_ROUTE} from "../constants";

function BasketModal({open, onClose}) {


    const {pizza} = useContext(Context) ;
    const {basketItems, clearCart, getBasketTotal, getBasketAmount} = useContext(ShopContext);
    const total = Math.floor(getBasketTotal()*100)/100;
    const navigate = useNavigate();
    if(!open) return null
    return (
        <>
            <Offcanvas show={open}
                       onHide = {onClose}
                       placement="end"
            >
                <Offcanvas.Header style = {{background : "black"}} closeButton >
                    <Offcanvas.Title style = {{color : "white"}}>Корзина</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    {(getBasketAmount() === 0) && <div>Корзина пуста!</div>}
                    {pizza.getPizzaProps().map((pizza) => {
                        if(basketItems[pizza.id] !== 0)
                        {
                            return  <PizzaItem pizza = {pizza}/>
                        }
                    }) }
                </Offcanvas.Body>
                {getBasketAmount() !== 0 ?
                    <div> Итоговая Сумма : {total} RUB
                    </div>
                    :
                    <></>
                }

                {getBasketAmount() !== 0 ?
                    <Button className="btn-success"
                            style = {{color : "White", background : "black"}}
                            onClick={() => {navigate(ORDER_ROUTE); onClose()}}>Оформить заказ</Button>
                    :
                    <></>
                }
                {getBasketAmount() !== 0 ?
                    <Button className="btn-danger"
                            onClick={() => clearCart()}>Очистить корзину</Button>
                    :
                    <></>
                }
            </Offcanvas>
        </>
    );
}

export default BasketModal;