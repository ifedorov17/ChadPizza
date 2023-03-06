import React from "react";

class BackButton extends React.Component {
    onclick () {
        window.location.assign('http://localhost:3000/');
    }

    render() {
        return (<button  onClick={(e) => this.onclick(e)}>Back </button>);
    }
}
export default BackButton;