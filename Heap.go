package main

import (
	"fmt"
	"container/heap"
	//"sort"
)

func main(){
	fmt.Println(topKFrequent([]string{"a","aa","aaa"}, 1))
}
type Node struct {
	Key string
	Count int
}

type PQ []*Node

//Heap interface 
func (pq PQ) Len() int { return len(pq) }
func (pq PQ) Swap(i, j int) { pq[i], pq[j] = pq[j], pq[i]}
func (pq PQ) Less(i, j int) bool {
	if pq[i].Count == pq[j].Count{
        return pq[i].Key > pq[j].Key //Bigger comes first
    }
	return pq[i].Count < pq[j].Count //Smaller comes first
}
func (pq *PQ) Push(node interface{}){
	x := node.(*Node)
	*pq = append(*pq, x)
}
func (pq *PQ) Pop() interface{} {
	temp := *pq
	l := len(temp)
	res := temp[l-1]
	*pq = temp[:l-1] //Same *pq = (*pq)[:l-1]
	return res
}


func topKFrequent(words []string, k int) []string {
	if words == nil || k == 0 { return nil}

	//Since WordMap is unordered - We have to make a slice to keep the order
	wordMap := make(map[string]int)
	
	for _, word := range words {
		wordMap[word]++	
	}

	pq := make(PQ, 0)
	i := 0
	for key, value := range wordMap {
		if i < k { 
			heap.Push(&pq, &Node{key, value})
		}else{
			//IMP : secont || condition to add values with same count but small key string
			if value > pq[0].Count || value == pq[0].Count && key < pq[0].Key {
				heap.Pop(&pq)
				heap.Push(&pq, &Node{key,value})
			}
		}
		i++
	}
	
	result := make([]string, 0)
	
	for len(pq) != 0 {
		result = append([]string{heap.Pop(&pq).(*Node).Key}, result...)
	}
    
   // sort.Sort(result)
   	// sort.Slice(result, func(i, j int) bool {
	// 	   if result[i].Count == result[j].Count {
	// 		   return result[i].Key < result[j].Key
	// 	   }
	// 	   return result[i].Count > result[j].Count
	//    })
    
    // temp := make([]string, 0)
    // for i:=0; i < len(result); i++ {
    //     temp = append(temp, result[i].Key)
    // }

	return result
}
