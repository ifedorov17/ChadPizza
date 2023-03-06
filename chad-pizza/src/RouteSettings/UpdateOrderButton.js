import React from "react";

class UpdateOrderButton extends React.Component {
    onclick () {
        window.location.assign('http://localhost:3000/update-order');
    }

    render() {
        return (<button  className = "btn btn-warning"
                         onClick={(e) => this.onclick(e)}>Update </button>);
    }
}
export default UpdateOrderButton;