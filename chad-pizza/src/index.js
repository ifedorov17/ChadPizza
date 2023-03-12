import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import ListOrderComponent from "./Components/ListOrderComponent";
import 'bootstrap/dist/css/bootstrap.min.css'
import App from "./App";
import OrderTableViewComponent from "./Components/OrderTableViewComponent";
import ControlPanelComponent from "./Components/ControlPanelComponent";
import UserStore from "./STORE/UserStore";
import PizzasStore from "./STORE/PizzasStore";

export const Context = createContext(null)

ReactDOM.render(
    <Context.Provider value = {{
        user : new UserStore(),
        pizza : new PizzasStore(),
    }}>
        <App/>,
    </Context.Provider>,
    document.getElementById('root')
);
