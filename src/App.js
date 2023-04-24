import AppRouter from "./Components/appRouter";
import {BrowserRouter} from "react-router-dom";
import NavBar from "./Components/navBar";
import {ShopContextProvider} from "./Context/allDeliveryContext";
import {useContext} from "react";
import {Context} from "./index";
import {ToastContainer} from "react-toastify";


function App() {

  return (
    <div>
        <ShopContextProvider>
            <BrowserRouter>
                <NavBar/>
                <AppRouter/>
            </BrowserRouter>
        </ShopContextProvider>
        <ToastContainer position="bottom-left"
                        autoClose={5000}
                        hideProgressBar={false}
                        newestOnTop={false}
                        closeOnClick
                        rtl={false}
                        pauseOnFocusLoss
                        draggable
                        pauseOnHover
                        theme="colored"
                        />
    </div>
  );
}

export default App;
