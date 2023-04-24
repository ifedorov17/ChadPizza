import React, {useContext} from 'react';
import {Context} from "../index";
import {Col, Container, Row} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import PizzaItem from "./pizzaItem";

const PizzaList = observer (() => {
    const {pizza} = useContext(Context)
    return (
        <Container>
            <Row className="d-flex p-md-1">
                {
                    pizza.getPizzaProps().map(item =>
                        <PizzaItem key = {item.name} pizza = {item}/>
                    )
                }
            </Row>
        </Container>
    );
});

export default PizzaList;