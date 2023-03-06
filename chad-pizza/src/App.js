import ListOrderComponent from "./Components/ListOrderComponent";
import OrderTableViewComponent from "./Components/OrderTableViewComponent";
import ControlPanelComponent from "./Components/ControlPanelComponent";
import FooterComponent from "./Components/FooterComponent";
import {BrowserRouter, Route, Routes, Link }from "react-router-dom"
import HeaderComponent from "./Components/ControlPanelComponent";
import Page404 from "./Pages/Page404";
import OrdersPage from "./Pages/OrdersPage";
import AddOrderPage from "./Pages/AddOrderPage";

/*function App() {
    return(
        <div>
            <BrowserRouter>
                <div className="container" >
                    <ControlPanelComponent/>
                        <div className = "container">
                            <Routes>
                                <Route path = "/" element= {ListOrderComponent}></Route>
                                <Route path = "/orders" element = {ListOrderComponent}></Route>
                                <ListOrderComponent/>,
                            </Routes>
                        </div>
                    <FooterComponent/>
                </div>
            </BrowserRouter>
        </div>

    )
}*/
/*function App() {
    return(
        <div>
            <BrowserRouter>
                <div className="container" >
                    {<HeaderComponent/>}
                    <div className = "container">
                        <Routes>
                            <Route path = "/" element= {<ListOrderComponent/>}/>}
                            <Route element = {<Page404/>} />
                        </Routes>
                    </div>
                    {<FooterComponent/>}
                </div>
            </BrowserRouter>
        </div>

    )
}*/
function App() {
    return(

        <div className = "container" >
            <ControlPanelComponent/>
            <BrowserRouter>
                <Routes>
                    <Route path = "/" element = {<OrdersPage/>}/>
                    <Route path = "/add-order" element = {<AddOrderPage/>}/>
                    <Route path = "*" element = {<Page404/>}/>
                </Routes>
            </BrowserRouter>
            <FooterComponent/>
        </div>
    )
}
export default App;