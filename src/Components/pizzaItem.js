import React, {useContext, useState} from 'react';
import {Accordion, Button, Card, Col, Image, Modal, Row} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import {PIZZA_ROUTE} from "../constants";
import {ShopContext} from "../Context/allDeliveryContext";

const PizzaItem = ({pizza}) => {
    const navigate = useNavigate()

    const {addToCart, removeFromCart, basketItems} = useContext(ShopContext);

    const cartItemAmount = basketItems[pizza.id]

    const [showModal, setShowModal] = useState(false);
    const handleClose = () => setShowModal(false);
    const handleShow = () => setShowModal(true);

    return (
        <Col className="mt-4 p-md-4">
            <Card style={ {width: '16rem', height : '21rem'}}
                 >
               {/* <Image width={150} height={150}
                       src={pizza.pictureUrl}></Image>*/}
                <Button className="rounded-circle bg-black d-flex
                                justify-content-center align-items-center"
                        onClick={handleShow}
                     style = {{
                         color : "white",
                         width : "1.5rem",
                         height : "1.5rem",
                         position : "absolute",
                         bottom : 0,
                         right : 0,
                         transform : "translate(25%,25%)"}}>i</Button>
                <Card.Img variant="top" src={pizza.pictureUrl} width={175} height={215}/>
            <Card.Body>
                <Row>
                    <div><h4>{pizza.name}</h4></div>
                    <div>Цена : {pizza.price} RUB</div>

                    <Modal show={showModal} onHide={handleClose}>
                        <Modal.Header closeButton>
                            <Modal.Title>Описание пиццы "{pizza.name}"</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>{pizza.description}</Modal.Body>
                    </Modal>
                    {pizza.isAvailable ?
                        <Row className="ms-0">
                            <Col className="ms-0">
                                <Button className = "btn-warning"
                                        style = {{width : 125}}
                                        onClick={() => addToCart(pizza.id)}>
                                    Добавить
                                    {cartItemAmount > 0 &&
                                        <>({cartItemAmount})</>}</Button>
                            </Col>
                            { cartItemAmount > 0  &&
                                <Col><Button className = "btn-danger"
                                             onClick={() => removeFromCart(pizza.id)}>X</Button> </Col>
                            }
                        </Row>
                        :
                        <Row>
                            Пицца недоступна
                        </Row>

                    }

                </Row>
            </Card.Body>
            </Card>
        </Col>

    );
};

export default PizzaItem;