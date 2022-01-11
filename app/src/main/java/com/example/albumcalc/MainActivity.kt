package com.example.albumcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import com.example.albumcalc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class  MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val bu:Int = Integer.parseInt(binding.editBu.text.toString())
            val meon:Int = Integer.parseInt(binding.editMeon.text.toString())


            if (binding.code11.isChecked) {
                binding.FinalPrice.text = code11(bu, meon)
            }
            else if (binding.code12.isChecked) {
                binding.FinalPrice.text = code12(bu, meon)
            }
            else if(binding.code12.isChecked) {
                binding.FinalPrice.text = code12(bu, meon)
            }
            else if(binding.code13.isChecked) {
                binding.FinalPrice.text = code13(bu, meon)
            }
            else if(binding.code21.isChecked) {
                binding.FinalPrice.text = code21(bu, meon)
            }
            else if(binding.code22.isChecked) {
                binding.FinalPrice.text = code22(bu, meon)
            }
            else if(binding.code23.isChecked) {
                binding.FinalPrice.text = code23(bu, meon)
            }


        }

    }

    fun code11(Bu:Int, Meon:Int):String {
        val price = HashMap<Int, Int>()
        price.put(30,33270)
        price.put(60,30840)
        price.put(90,29360)
        price.put(120,28240)
        price.put(150,27950)
        price.put(180,25110)
        price.put(210,24640)
        price.put(240,24450)
        price.put(270,24290)
        price.put(300,24310)
        price.put(330,24040)
        price.put(360,23910)
        price.put(390,23830)
        price.put(420,23780)
        price.put(450,23720)
        price.put(480,23660)
        price.put(510,23560)
        price.put(540,23590)
        price.put(570,23570)
        price.put(600,23560)
        price.put(630,23530)

        val count = HashMap<Int, Int>()
        count.put(30,14)
        count.put(60,18)
        count.put(90,22)
        count.put(120,26)
        count.put(150,30)
        count.put(180,34)
        count.put(210,38)
        count.put(240,42)
        count.put(270,46)
        count.put(300,50)
        count.put(330,54)
        count.put(360,58)
        count.put(390,62)
        count.put(420,66)
        count.put(450,70)
        count.put(480,74)
        count.put(510,78)
        count.put(540,82)
        count.put(570,86)
        count.put(600,90)
        count.put(630,95)

        System.out.println("--------------------------------------------")

        val a:Int = price.get(Bu-Bu%30)?: 0
        System.out.println(a)
        val m:Int = count.get(Bu-Bu%30)?:0
        System.out.println(m)
        val b:Double = a.toDouble() / (m+8).toDouble()
        System.out.println(b)

        System.out.println("--------------------------------------------")
        val a2:Int = price.get(Bu+(30-(Bu%30)))?: 0
        System.out.println(a2)
        val m2:Int = count.get(Bu+(30-(Bu%30)))?: 0
        System.out.println(m2)
        val b2:Double = a2.toDouble() / (m2+8).toDouble()
        System.out.println(b2)

        System.out.println("--------------------------------------------")

        val c:Double = b2+((b-b2)/30*((Bu+(30-(Bu%30)))-Bu))
        System.out.println(c)
        val d:Double = c * (Meon+8)
        System.out.println(d)

        System.out.println("--------------------------------------------")

        var final = d - d%10

        if (binding.IronBand.isChecked) {
            final += 970
        }
        if (binding.personal.isChecked) {
            final += 880
        }
        if(binding.IronCover.isChecked) {
            final += 600
        }
        if (binding.ThreeFloor.isChecked) {
            final += 1860
        }


        return final.toString()

    }






}