import React, {createContext} from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import 'bootstrap/dist/css/bootstrap.css'
import PizzaStore from "./Store/pizzaStore";

export const Context = createContext(null)
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <Context.Provider value = {{
        pizza : new PizzaStore(),
    }}>
        <App/>
    </Context.Provider>
);

