import React, {useContext} from 'react';
import {ShopContext} from "../Context/allDeliveryContext";
import {Context} from "../index";
import {Form} from "react-bootstrap";

const OrderForm = ({propsArray}) => {

    const {basketItems} = useContext(ShopContext);
    const {pizza} = useContext(Context);
    const changeNameHandler = (e) => {
        e.preventDefault();
        propsArray[0](e.target.value);
    }
    const changeSurnameHandler = (e) => {
        e.preventDefault();
        propsArray[1](e.target.value);
    }
    const changeMiddlenameHandler = (e) => {
        e.preventDefault();
        propsArray[2](e.target.value);
    }
    const changeAdressHandler = (e) => {
        e.preventDefault();
        propsArray[3](e.target.value);
    }
    const changePhonenumberHandler = (e) => {
        e.preventDefault();
        propsArray[4](e.target.value);
    }

    return (
        <div>
            <Form>
                <Form.Group className="mb-3" controlId="FirstName">
                    <Form.Label> Имя <h10 style = {{color : 'red'}}>*</h10> </Form.Label>
                    <Form.Control  onChange = {changeNameHandler} type="text" placeholder="Введите имя" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="SecondName">
                    <Form.Label> Фамилия <h10 style = {{color : 'red'}}>*</h10>  </Form.Label>
                    <Form.Control onChange = {changeSurnameHandler} type="text" placeholder="Введите фамилию" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="MiddleName">
                    <Form.Label> Отчество </Form.Label>
                    <Form.Control onChange={changeMiddlenameHandler} type="text" placeholder="Введите отчество" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="MiddleName">
                    <Form.Label> Контактный номер <h10 style = {{color : 'red'}}>*</h10>  </Form.Label>
                    <Form.Control onChange={changePhonenumberHandler} type="tex"
                                  placeholder="+7(XXX)XXXXXXX"
                                  pattern="[0-9]*" inputMode="numeric" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="MiddleName">
                    <Form.Label> Адрес доставки <h10 style = {{color : 'red'}}>*</h10>  </Form.Label>
                    <Form.Control onChange={changeAdressHandler} type="text" placeholder="г. Одинцово, ул. Пушкина, д. Колотушкина" />
                </Form.Group>

            </Form>
        </div>
    );
};

export default OrderForm;