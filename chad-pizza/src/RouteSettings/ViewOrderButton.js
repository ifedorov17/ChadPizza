import React from "react";

class ViewOrderButton extends React.Component {
    onclick () {
        window.location.assign('http://localhost:3000/view-order');
    }

    render() {
        return (<button  onClick={(e) => this.onclick(e)}>Back </button>);
    }
}
export default ViewOrderButton;