import React from 'react';
import {Card, Col, Image, Row} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import {PIZZA_ROUTE} from "../utils/consts";
const PizzaItem = ({pizza}) => {
    const navigate = useNavigate()

    return (
            <Col className="container-fluid mt-4 p-md-5"
                 onClick = {() => navigate(PIZZA_ROUTE + '/' + pizza.name)}>
                <Card style={{width : 200, cursor : 'pointer'}}
                      border={"light"}>
                    <Image width={200} height={200}
                           src={pizza.img}></Image>
                    <Row className = "d-flex justify-content-between ">
                        <div>{pizza.name}</div>
                        <div>Цена : {pizza.price} RUB</div>
                    </Row>
                </Card>
            </Col>
    );
};

export default PizzaItem;