const f1 = async () => {
  let a = 0;
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      a = 1;
      resolve(a);
    }, 2000);
  });
};

async function main() {
  let a = await f1();
  let b;
  f1().then((data) => {
    b = data;
    console.log(b);
  });
  console.log(b, "outside");
  console.log(a);
}

main();
