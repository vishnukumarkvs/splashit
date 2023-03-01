import React from "react";
import CollectionsWear from "../components/CollectionsWear";
import Navbar from "../components/Navbar";

function Home(){
    return (
        <div className="bg-[#feee80] h-full">
            <Navbar/>
            <CollectionsWear/>
        </div>
    );
}

export default Home;