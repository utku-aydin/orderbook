import React from "react";
import { Table, Button } from "react-bootstrap";

class OrderAdjuster extends React.Component{

    state = {
        value: ""
    };

    constructor(props){
        super(props);
        this.state.value = this.props.value;
    }

    render(){
        console.log("VAlue in order adjuster is " + this.state.value)
        return (
        <p>{this.state.value}</p>
        )
    }


}

export default OrderAdjuster;
