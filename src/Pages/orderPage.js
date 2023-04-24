import React, {useContext, useState} from 'react';
import {ShopContext, Toasty} from "../Context/allDeliveryContext";
import {Context} from "../index";
import {Button, Col, Container, Form, ListGroup, Row, Toast, ToastContainer} from "react-bootstrap";
import PizzaItem from "../Components/pizzaItem";
import {useNavigate} from "react-router-dom";
import {MENU_ROUTE} from "../constants";
import OrderForm from "../Components/orderForm";
import {PIZZAS} from "../Context/allDeliveryContext";
import CRUDService from "../CRUDService";
import {toast} from "react-toastify";

const OrderPage = () => {

    const {basketItems, getBasketAmount, getBasketTotal, clearCart} = useContext(ShopContext);
    const {pizza} = useContext(Context)
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [middlename, setMiddlename] = useState('');
    const [adress, setAdress] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const propsArray = [
        setName,
        setSurname,
        setMiddlename,
        setAdress,
        setPhoneNumber,
    ]
    const orderPropsJSON = () =>  {

        const orderProps = {customerFirstName : name,
            customerSurname: surname,
            customerMiddleName : middlename,
            customerPhoneNumber : phoneNumber,
            customerAddress : adress,
            orderTotalPrice : getBasketTotal(),
            orderPositions : []}
        for(const item in basketItems)
        {
            if(basketItems[item] > 0) {
                let itemInfo = PIZZAS.find((el) => el.id === Number(item));
                orderProps['orderPositions'].push({pizzaName : itemInfo.name,
                    count: basketItems[item]});

            }
        }
        return orderProps;
    }

    const phoneRegEx = new RegExp("^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$");
    const validate = () => {
        console.log(phoneRegEx.test(phoneNumber))
        if(name === '' || surname === ''  ||
            phoneNumber === '' || adress === '')
        {
            return 1;
        }
        else if (!phoneRegEx.test(phoneNumber))
        {
            return 2;
        }
        else {
            return 3;
        }
    }

    const onSubmitHandler = () =>
    {
        if(validate() === 1)
        {
            toast.error('Пустые контактные данные! Проверьте ввод',
                {position: "bottom-left",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "colored",});
            return;
        }else if(validate() === 2 )
        {
            toast.error('Телефонный номер указан некорректно!',
                {position: "bottom-left",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "colored",});
            return;
        }
        CRUDService.postOrder(orderPropsJSON()).then((res) => {
            // дальше здесь надо написать обработку респонса
            // и выкинуть тост по респонсу или саксес или нет
            console.log(res.status);
            if(res.status === 200)
            {
                navigate(MENU_ROUTE);
                clearCart();
                    toast.success('Заказ успешно добавлен по адресу ' + res.data.customerAddress,
                        {position: "bottom-left",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                        theme: "colored",});
            }
        }).catch((err) => {
            if(err.response.status === 403 || err.response.status === 401 )
            {
                toast.error("На складе недостаточно ингредиентов для этого заказа! ", {position: "bottom-left",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "colored",})
            }
            else {
                toast.error("Неизвестная ошибка ", {position: "bottom-left",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "colored",})
            }

        });

    }

    return (
        <Row>
            <Col>
                <ListGroup>
                    <h1>Заказ:</h1>
                    {(getBasketAmount() === 0) && <Container>Заказ Пуст! <Button
                        onClick={() => {navigate(MENU_ROUTE)}}
                        style = {{background : "black"}}>Вернуться к меню</Button></Container>}
                    {pizza.getPizzaProps().map((pizza) => {
                        if(basketItems[pizza.id] !== 0)
                        {
                            return <ListGroup.Item><PizzaItem pizza = {pizza}/></ListGroup.Item>
                        }
                    }) }
                </ListGroup>
            </Col>
            <Col>
                { (getBasketAmount() !== 0) &&
                <ListGroup>
                    <h1>Контактные данные:</h1>
                    <ListGroup.Item>
                        <OrderForm propsArray={propsArray}></OrderForm>
                    </ListGroup.Item>

                    <ListGroup.Item>
                        <Form>
                            <Row>
                                <Col>
                                    Стоимость заказа: {getBasketTotal()} RUB
                                </Col>
                                <Col>
                                    <Button onClick={onSubmitHandler} className="btn-warning">Сделать заказ!</Button>
                                </Col>
                            </Row>
                        </Form>
                        </ListGroup.Item>
                    <Row style={{color : 'red'}}>
                        * - обязательные поля
                    </Row>
                </ListGroup>
                }
            </Col>
        </Row>
    );
}

export default OrderPage;