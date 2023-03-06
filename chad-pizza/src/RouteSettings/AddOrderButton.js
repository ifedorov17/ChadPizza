import React from "react";

class AddOrderButton extends React.Component {
    onclick () {
        window.location.assign('http://localhost:3000/add-order');
    }

    render() {
        return (<button className="btn btn-warning"
                        onClick={(e) => this.onclick(e)}>Add Order </button>);
    }
}
export default AddOrderButton;

