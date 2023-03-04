const BottomFeed = () => {
  return (
    <div className="w-[85%] mx-auto">
      <div className="flex p-10 items-center justify-center">
        <div className="flex-1 flex justify-center ">
          <h1 className="font-bree font-bold text-4xl">
            Fashion gives us impression where Innovation meets Art and
            Craft.
          </h1>
        </div>
        <div className="flex-1 flex justify-center">
          <a
            href="#_"
            class="relative inline-block px-4 py-2 font-medium group"
          >
            <span class="absolute inset-0 w-full h-full transition duration-200 ease-out transform translate-x-1 translate-y-1 bg-black group-hover:-translate-x-0 group-hover:-translate-y-0"></span>
            <span class="absolute inset-0 w-full h-full bg-white border-2 border-black group-hover:bg-black"></span>
            <span class="relative text-black group-hover:text-white">
              View All
            </span>
          </a>
        </div>
      </div>
    </div>
  );
};

export default BottomFeed;
