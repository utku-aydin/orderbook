import React from "react";
import { Table, Button, Col, Row } from "react-bootstrap";
import { ArrowUpShort, ArrowDownShort } from 'react-bootstrap-icons'
import styled from 'styled-components';

const HoverText = styled.p`
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
    }

    render() {
        console.log("VAlue in order adjuster is " + this.state.value)
        return (
            <React.Fragment>
                <Row sm="12">
                    {this.state.value}
                    <HoverText><div width="10" height="5" onMouseOver="this.style.cursor='pointer'">
                        <Col>
                            <ArrowUpShort />
                        </Col>
                        <Col>
                            <ArrowDownShort />
                        </Col>
                        </div></HoverText>
                </Row>
            </React.Fragment>
        )
    }


}

export default OrderAdjuster;
