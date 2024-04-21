

import React from "react";
import { Nav, NavLink, NavMenu }
    from "./NavbarElements";

const Navbar = () => {
    return (
        <>
            <Nav>
                <NavMenu>
                    <NavLink to="/search" activeStyle>
                        Search
                    </NavLink>
                </NavMenu>
            </Nav>
        </>
    );
};

export default Navbar;
