import React, {useContext, useState} from 'react';
import {Container, Nav, Navbar, NavDropdown, Button} from "react-bootstrap";
import BasketModal from "./basketModal";
import {GrBasket} from "react-icons/gr";
import {ShopContext} from "../Context/allDeliveryContext";
import {useNavigate} from "react-router-dom";
import {MENU_ROUTE} from "../constants";


const NavBar = () => {
    const [openBasket, setOpenBasket] = useState(false)
    const {basketItems, getBasketAmount} = useContext(ShopContext);
    const navigate = useNavigate();
    return (
            <Navbar bg="dark" variant="dark" sticky="top" className="nav-collapse no-transition">
                    <Navbar.Brand href="">Chad Pizza Delivery</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />

                        <Nav>
                            <Nav.Link onClick={() => {navigate(MENU_ROUTE)}}>Меню</Nav.Link>
                        </Nav>
                        <Nav className="ms-auto">
                            <Button style = {{width : "3rem", height : "3rem", position : "relative"}}
                                    variant="warning"
                                    onClick={() => setOpenBasket(true)}>
                                <GrBasket/>
                                {(getBasketAmount() !== 0) && <div className="rounded-circle bg-danger d-flex
                                justify-content-center align-items-center"
                                     style = {{
                                         color : "white",
                                         width : "1.5rem",
                                         height : "1.5rem",
                                         position : "absolute",
                                         bottom : 0,
                                         right : 0,
                                         transform : "translate(25%,25%)"}}>{getBasketAmount()}</div>}
                            </Button>
                            <BasketModal open = {openBasket}
                                         onClose = {() => setOpenBasket(false)}/>
                            <Nav.Link href="https://github.com/ifedorov17/ChadPizza">Github Source</Nav.Link>
                        </Nav>


            </Navbar>
    );
};

export default NavBar;