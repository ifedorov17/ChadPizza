import HeaderComponent from "../Components/ControlPanelComponent";
import FooterComponent from "../Components/FooterComponent";
import React, {Component} from 'react';
import AddOrderModalComponent from "../Functions/AddOrderModal";

class Page404 extends Component {
    render() {
        return (
            <div className = "container">
                <AddOrderModalComponent/>
               {/*<button className='btn-primary'
                       onClick={() => AddOrderModalComponent}> Add Order</button>*/}
            </div>
        );
    }
}

export default Page404;