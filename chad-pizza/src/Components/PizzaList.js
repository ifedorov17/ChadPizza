import React, {useContext} from 'react';
import {Context} from "../index";
import {Col, Container, Row} from "react-bootstrap";
import PizzaItem from "./PizzaItem";
import {observer} from "mobx-react-lite";

const PizzaList = observer (() => {
    const {pizza} = useContext(Context)
    return (
        <Container className="p-md-1">
                <Row className="d-flex p-md-1">
                    {
                        pizza.pizzaProps.map(pizza =>
                            <PizzaItem key = {pizza.name} pizza = {pizza}/>
                        )
                    }
                </Row>
        </Container>
    );
});

export default PizzaList;