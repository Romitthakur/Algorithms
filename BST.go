package main

import (
	"fmt"
)

func main(){
	bst := new(BST)
	bst.insert(1,2)
	bst.insert(5,6)
	bst.insert(7,8)
	bst.insert(9,10)

	fmt.Println(bst.get(1))
	fmt.Println(bst.get(2))
	fmt.Println(bst.get(3))
	fmt.Println(bst.get(4))
	fmt.Println(bst.get(5))

	fmt.Println(bst.floor(4))
	fmt.Println(bst.floor(100))
	fmt.Println(bst.ceil(6))
}

type Node struct {
	Key int
	Val int
	Left *Node
	Right *Node
}

type BST struct {
	root *Node
}

func(bst *BST) insert(key, val int){
	bst.root = insertHelper(bst.root, key, val)
}

func insertHelper(x *Node, key, val int) *Node {
	if x == nil { return &Node{key, val, nil, nil}}
	
	if (x.Key < key){
		x.Right = insertHelper(x.Right,key,  val)
	}else if (x.Key > key){
		x.Left = insertHelper(x.Left, key, val)
	}else {
		x.Val = val
	}
	return x
}

func (bst *BST) get(key int) (int, bool) {
	x := getHelper(bst.root, key)
	if x == nil { return 0, false }
	return x.Val, true
}

func getHelper(x *Node, key int) *Node{
	if x == nil { return nil }
	if x.Key < key {
		return getHelper(x.Right, key)
	}else if x.Key > key {
		return getHelper(x.Left, key)
	}else {
		return x
	}
}

func (bst *BST) floor(key int) (int, bool) {
	x := floorHelper(bst.root, key)
	if x == nil {return 0, false}
	return x.Key, true
}

func floorHelper(x *Node, key int) *Node {
	if x == nil { return nil}
	if x.Key == key {
		return x
	}else if x.Key > key {
		return floorHelper(x.Left, key)
	}else{
		t := floorHelper(x.Right, key)
		if t != nil {
			return t
		}
	}
	return x
}

func (bst *BST) ceil(key int) (int, bool) {
	x := ceilHelper(bst.root, key)
	if x == nil { return 0 , false}
	return x.Key, true
}

func ceilHelper(x *Node, key int) *Node {
	if x == nil { return nil }
	if x.Key == key {
		return x
	}else if x.Key < key {
		return ceilHelper(x.Right, key)
	}else{
		t := ceilHelper(x.Left, key)
		if t != nil {
			return t
		}
	}
	return x
}
