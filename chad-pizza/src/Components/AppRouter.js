import React, {useContext} from 'react';
import {Routes, Route, Navigate} from 'react-router-dom'
import {authRoutes} from "../routes";
import {publicRoutes} from "../routes";
import {MENU_ROUTE} from "../utils/consts";
import MenuPage from "../Pages/MenuPage";
import {Context} from "../index";

const AppRouter = () => {
    const {user} = useContext(Context)
    console.log(user)
    return (
        <div>
            <Routes>
                {user.isAuth && authRoutes.map(({path, Component}) =>
                    <Route key ={path} path = {path} element={<Component/>} />
                )}
                {publicRoutes.map(({path, Component}) =>
                    <Route key ={path} path = {path} element={<Component/>} />
                )}
                <Route path = '*' element = {<Navigate to={"/"}/>}/>
            </Routes>
        </div>
    );
};

export default AppRouter;