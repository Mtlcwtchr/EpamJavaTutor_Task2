package jv.usinggdm.DynamicArray;

import jv.usinggdm.Noteandpad.Note;

import java.util.Arrays;

public class DynamicArray {

            private Note[] array;

                public DynamicArray(Note[] array){
                    this.array = array;
                }
                    public DynamicArray(){}

            // Functions
            public Note[] getArray(){
                return this.array;
            }
            public void append(Note element){
                    if(element==null) return;
                    if(this.array == null){
                        this.array = new Note[1];
                        this.array[0] = element;
                    } else{
                        this.array = Arrays.copyOf(this.array, this.array.length+1);
                            this.array[this.array.length-1] = element;
                    }
            }
            public void print(){
                System.out.print("{");
                    for(Note i: array)
                        System.out.print("["+i+"]");
                 System.out.println("}");
            }
            public void removeTop(){
                    this.array = Arrays.copyOf(this.array, this.array.length-1);
            }
            public int length(){
                    return this.array.length;
            }
            // Functions
}
