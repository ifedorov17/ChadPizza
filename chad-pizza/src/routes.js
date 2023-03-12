import AdminPage from "./Pages/AdminPage";
import {ADMIN_ROUTE, BASKET_ROUTE, LOGIN_ROUTE, MENU_ROUTE, PIZZA_ROUTE, REGISTRATION_ROUTE} from "./utils/consts";
import BasketPage from "./Pages/BasketPage";
import MenuPage from "./Pages/MenuPage";
import Auth from "./Pages/Auth";
import PizzaPage from "./Pages/PizzaPage";

export const authRoutes = [
    {
        path : ADMIN_ROUTE,
        Component : AdminPage
    },
    {
        path : BASKET_ROUTE,
        Component : BasketPage
    },

]

export const publicRoutes = [
    {
        path : MENU_ROUTE,
        Component : MenuPage
    },
    {
        path : LOGIN_ROUTE,
        Component : Auth
    },
    {
        path : REGISTRATION_ROUTE,
        Component : Auth
    },
    {
        path : PIZZA_ROUTE + '/:name',
        Component : PizzaPage
    },
]