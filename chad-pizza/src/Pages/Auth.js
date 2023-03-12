import React, {useContext, useState} from 'react';
import {Button, Card, Container, Form, Row} from "react-bootstrap";
import {ADMIN_ROUTE, LOGIN_ROUTE, REGISTRATION_ROUTE} from "../utils/consts";
import {NavLink, useLocation, useNavigate} from "react-router-dom";
import {Context} from "../index";

const Auth = () => {
    const location = useLocation()
    const isLogin = location.pathname === LOGIN_ROUTE
    const [email, setEmail] = useState(null)
    const [password, setPassword] = useState(null)
    const {user} = useContext(Context)
    const navigate = useNavigate()


    const changeLog = () => {
        if (email === "admin@gmail.com" && password === "13371337") {
            user._isAuth = true
        }
       navigate(ADMIN_ROUTE)
    }

    return (
        <Container
            className = "d-flex justify-content-center align-items-center"
            style = {{height : window.innerHeight - 54}} >
            <Card style = {{width : 1000}} className = "p-5">
                <h2 className="m-auto">{isLogin ? 'Авторизация' : 'Регистрация'}</h2>
                <Form className = "col" >
                    <Form.Control
                        className="mt-4"
                        placeholder="Введите логин"
                        onChange = {e => setEmail(e.target.value)}
                    />
                    <Form.Control
                        className="mt-4"
                        placeholder="Введите пароль"
                        onChange = {e => setPassword(e.target.value)}
                        type = "password"
                    />
                </Form>
                <Row className = "d-flex justify-content-between mt-4 pl-3 pr-4 ">
                    {isLogin ?
                        <div>
                            Нет аккаунта? <NavLink to = {REGISTRATION_ROUTE}>Регистрация</NavLink>
                        </div>
                        :
                        <div>
                        Есть аккаунт? <NavLink to = {LOGIN_ROUTE}>Войдите</NavLink>
                        </div>
                    }
                        <Button variant = {"outline-primary"}
                                onClick = {() => changeLog()}
                        >Войти</Button>


                </Row>
            </Card>
        </Container>

    );
};

export default Auth;