import React from "react";
import { Table, Button, Col, Row } from "react-bootstrap";
import { ArrowUpShort, ArrowDownShort } from 'react-bootstrap-icons'
import styled from 'styled-components';

const GreenPointer = styled.p`
	color: #000;
	:hover {
		color: 	#008000;
		cursor: pointer;
	}
`
const RedPointer = styled.p`
	color: #000;
	:hover {
		color: #ed1212;
		cursor: pointer;
	}
`
const RedText = styled.p`
    color: #ed1212;
`

const GreenText = styled.p`
    color: #008000;
`
class OrderAdjuster extends React.Component {

//     state = {
//         value: ""
//     };

//     constructor(props) {
//         super(props);
//         this.state.value = this.props.value;
//         this.addOne = this.addOne.bind(this);
//         this.minusOne = this.minusOne.bind(this);
//     }

//  addOne() {
//     this.setState({ value: this.state.value + 1 });

// }

// minusOne(){
//     this.setState({ value: this.state.value - 1 });
// }


render() {
    console.log("VAlue in order adjuster is " + this.props.value)
    return (
        <React.Fragment>
            <Row sm="12">
                {this.props.value}
                <div width="10" height="5" >
                    <Col>
                        <GreenPointer onClick={(evt) => this.props.handleChangeNumber(this.props.name,this.props.tickSize,evt)}><ArrowUpShort /></GreenPointer>
                    </Col>
                    <Col>
                        <RedPointer onClick={(evt) => this.props.handleChangeNumber(this.props.name,-this.props.tickSize,evt)}><ArrowDownShort /></RedPointer>
                    </Col>
                </div>
                {this.props.editValue >= this.props.value ? null : <RedText>{this.props.editValue}</RedText>}
                {this.props.editValue <= this.props.value ? null : <GreenText>{this.props.editValue}</GreenText>}
            </Row>
        </React.Fragment>
    )
}


}

export default OrderAdjuster;
