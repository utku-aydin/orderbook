import React from "react";
import { Table, Button, Col, Row } from "react-bootstrap";
import { ArrowUpShort, ArrowDownShort } from 'react-bootstrap-icons'
import styled from 'styled-components';

const GreenText = styled.p`
	color: #000;
	:hover {
		color: 	#008000;
		cursor: pointer;
	}
`
const RedText = styled.p`
	color: #000;
	:hover {
		color: #ed1212;
		cursor: pointer;
	}
`
class OrderAdjuster extends React.Component {

    state = {
        value: ""
    };

    constructor(props) {
        super(props);
        this.state.value = this.props.value;
        this.addOne = this.addOne.bind(this);
        this.minusOne = this.minusOne.bind(this);
    }

 addOne() {
    this.setState({ value: this.state.value + 1 });

}

minusOne(){
    this.setState({ value: this.state.value - 1 });
}


render() {
    console.log("VAlue in order adjuster is " + this.state.value)
    return (
        <React.Fragment>
            <Row sm="12">
                {this.state.value}
                {this.props.editValue}
                <div width="10" height="5" >
                    <Col>
                        <GreenText value={5} onClick={(evt) => this.props.addOne(this.props.name,evt)}><ArrowUpShort /></GreenText>
                    </Col>
                    <Col>
                        <RedText onClick={this.minusOne}><ArrowDownShort /></RedText>
                    </Col>
                </div>
            </Row>
        </React.Fragment>
    )
}


}

export default OrderAdjuster;
