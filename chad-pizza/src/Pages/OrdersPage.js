import React, {Component} from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HeaderComponent from "../Components/ControlPanelComponent";
import ListOrderComponent from "../Components/ListOrderComponent";
import Page404 from "./Page404";
import FooterComponent from "../Components/FooterComponent";
import AddOrderModalComponent from "../Functions/AddOrderModal";

class OrdersPage extends Component {
    render() {
        return (
            <div>
                <div>
                    <div className="container" >
                        <div className = "container">
                            <ListOrderComponent/>,
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default OrdersPage;