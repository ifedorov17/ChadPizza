import React from 'react';
import {Navigate, Route, Routes} from "react-router";
import {publicRoutes} from "../routes";

const AppRouter = () => {
    return (
        <div>
            <Routes>
                {publicRoutes.map(({path, Component}) =>
                    <Route key ={path} path = {path} element={<Component/>} />
                )}
                <Route path = '*' element = {<Navigate to={"/"}/>}/>
            </Routes>
        </div>
    );
};

export default AppRouter;