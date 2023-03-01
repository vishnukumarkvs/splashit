import React from "react";

import {FiSearch} from 'react-icons/fi';
import {BsHandbag} from 'react-icons/bs';
import {HiSquares2X2} from 'react-icons/hi2'

const Navbar = () => {
    return(
        <div className="flex flex-row justify-between p-3">
            <div className="flex justify-between gap-x-4 ml-8">
                <div className="font-caveat text-2xl">
                    Flare
                </div>
                <div className="flex flex-row gap-x-5 ml-20 font-bold items-center">
                    <p>NEW ARRIVALS</p>
                    <p>COLLECTIONS</p>
                    <p>ABOUT US</p>
                    <p>BLOG</p>
                </div>
            </div>
            <div className="flex gap-x-6 mx-3 items-center">
                <FiSearch/>
                <div className="flex">
                   <div className=" border-2 border-black px-1 pt-1"><BsHandbag/></div>
                   <div className="border-y-2 border-r-2 border-black px-2"><p className="font-bold">0</p></div>
                </div>
                <HiSquares2X2/>
            </div>
        </div>
    );
}

export default Navbar;