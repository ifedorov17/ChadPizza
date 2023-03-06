import React, { Component } from 'react'

class FooterComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <footer className = "footer">
                    <div
                        className="footer-copyright text-center py-3">
                        All Rights Reserved @ChadPizza 2023
                    </div>
                </footer>
            </div>
        )
    }
}

export default FooterComponent