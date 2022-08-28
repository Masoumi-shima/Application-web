<template>
  <button>{{ title }}</button>
</template>

<script>
export default {
  props: {
    title: String
  }
}
</script>

<style>
button {
  font-size: 17px;
  padding: 10px;
  background: rgb(0, 128, 240);
  border: 1px solid rgb(0, 0, 0);
  border-radius: 10px;
  margin: 10px;
  transition: all 0.5s;
  cursor: pointer;
  color: aliceblue
}

button:hover {
  background-color: rgb(4, 56, 109);
  color: white;
}
</style>