import {BASKET_ROUTE, MENU_ROUTE, ORDER_ROUTE, PIZZA_ROUTE} from "./constants";
import menuPage from "./Pages/menuPage";
import basketPage from "./Pages/basketPage";
import pizzaPage from "./Pages/pizzaPage";
import orderPage from "./Pages/orderPage";

export const publicRoutes = [
    {
        path : MENU_ROUTE,
        Component : menuPage
    },
    {
        path : BASKET_ROUTE,
        Component : basketPage
    },
    {
        path : PIZZA_ROUTE + '/:id',
        Component : pizzaPage
    },
    {
        path : ORDER_ROUTE,
        Component : orderPage
    },
]

