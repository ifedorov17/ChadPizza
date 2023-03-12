import ListOrderComponent from "./Components/ListOrderComponent";
import ControlPanelComponent from "./Components/ControlPanelComponent";
import FooterComponent from "./Components/FooterComponent";
import {BrowserRouter, Route, Routes, Link }from "react-router-dom"
import HeaderComponent from "./Components/ControlPanelComponent";
import Page404 from "./Pages/Page404";
import OrdersPage from "./Pages/OrdersPage";
import AppRouter from "./Components/AppRouter";
import NavBar from "./Components/NavBar";

/*function App() {
    return(

        <div className = "container" >
            <ControlPanelComponent/>
            <BrowserRouter>
                <Routes>
                    <Route path = "/" element = {<OrdersPage/>}/>
                    <Route path = "*" element = {<Page404/>}/>
                </Routes>
            </BrowserRouter>
            <FooterComponent/>
        </div>
    )
}*/

function App() {
    return(
        <BrowserRouter>
            <NavBar/>
            <AppRouter />
        </BrowserRouter>
    );
};
export default App;