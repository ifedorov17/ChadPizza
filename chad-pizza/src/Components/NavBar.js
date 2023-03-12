import React, {useContext} from 'react';
import {Context} from "../index";
import {Button, Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {NavLink, useNavigate} from "react-router-dom";
import {BASKET_ROUTE, LOGIN_ROUTE, MENU_ROUTE} from "../utils/consts";
import {observer} from "mobx-react-lite";

const NavBar = observer ( () => {
    const {user} = useContext(Context)
    const navigate = useNavigate()
    return (
        <div>
            <Navbar className = "d-flex  mb-3"
                    bg="dark"
                    variant="dark">
                <Container>
                    <NavLink className = "me-auto p-2 "
                             to={MENU_ROUTE}>ChadPizza</NavLink>
                    { user.isAuth ?
                        <Nav className="ms-auto" style = {{color : "white"}}>
                            {user.isAuth && <Button variant = {"outline-light"}>
                                Корзина</Button>}
                            <Button variant = {"outline-light"}
                                    className = 'ms-4'
                                    onClick={() => user.setIsAuth(false)}>Выйти</Button>
                        </Nav>
                        :
                        <Nav className="p-2" style = {{color : "white"}}>
                            <Button variant = {"outline-light"}
                                    onClick={() => navigate(LOGIN_ROUTE)}>Авторизация</Button>
                        </Nav>
                    }
                </Container>
            </Navbar>
        </div>
    );
});

export default NavBar;