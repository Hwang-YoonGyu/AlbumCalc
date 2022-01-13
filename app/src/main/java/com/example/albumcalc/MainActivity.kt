package com.example.albumcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import com.example.albumcalc.databinding.ActivityMainBinding


import java.text.DecimalFormat
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.time.measureTimedValue

class  MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            binding.Alert.text = ""
            if (!(binding.code11.isChecked)&&!(binding.code12.isChecked)&&!(binding.code13.isChecked)&&!(binding.code21.isChecked)&&!(binding.code22.isChecked)&&!(binding.code23.isChecked)) {
                binding.Alert.text = "코드를 선택하세요"
                return@setOnClickListener
            }

            var bu: Int = 0
            var meon: Int = 0

            if(binding.editBu.text.toString()!="")
                bu =Integer.parseInt(binding.editBu.text.toString())
            else
                binding.Alert.text = "부수와 면수를 다시 확인하세요"

            if (binding.editMeon.text.toString()!="")
                meon = Integer.parseInt(binding.editMeon.text.toString())
            else
                binding.Alert.text = "부수와 면수를 다시 확인하세요"

            binding.FinalPrice.text = calc(bu, meon)
        }
        binding.Woo2.setOnClickListener {
            //binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.Woo3.setOnClickListener {
            binding.Woo2.isChecked = false
            //binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.normalPU2.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            //binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.normalPU3.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            //binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.royalPU2.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            //binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.royalPU3.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            //binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.color2.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            //binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.color3.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            //binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.fourCross2.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            //binding.fourCross2.isChecked = false
            binding.fourCross3.isChecked = false
        }
        binding.fourCross3.setOnClickListener {
            binding.Woo2.isChecked = false
            binding.Woo3.isChecked = false
            binding.normalPU2.isChecked = false
            binding.normalPU3.isChecked = false
            binding.royalPU2.isChecked = false
            binding.royalPU3.isChecked = false
            binding.color2.isChecked = false
            binding.color3.isChecked = false
            binding.fourCross2.isChecked = false
            //binding.fourCross3.isChecked = false
        }
    }

    fun calc(Bu:Int, Meon:Int):String {
        val price = HashMap<Int, Int>()
        if (binding.code11.isChecked) {
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
        }
        else if (binding.code12.isChecked) {
            price.put(30,30140)
            price.put(60,28390)
            price.put(90,27310)
            price.put(120,26510)
            price.put(150,26320)
            price.put(180,24250)
            price.put(210,23930)
            price.put(240,23820)
            price.put(270,23730)
            price.put(300,23770)
            price.put(330,23000)
            price.put(360,22940)
            price.put(390,22890)
            price.put(420,22890)
            price.put(450,22840)
            price.put(480,22810)
            price.put(510,22840)
            price.put(540,22850)
            price.put(570,22860)
            price.put(600,22880)
            price.put(630,22880)
        }
        else if(binding.code13.isChecked) {
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
        }
        else if(binding.code21.isChecked) {
            price.put(30,30120)
            price.put(60,27670)
            price.put(90,26180)
            price.put(120,25060)
            price.put(150,24760)
            price.put(180,21910)
            price.put(210,21430)
            price.put(240,21240)
            price.put(270,21070)
            price.put(300,21090)
            price.put(330,20810)
            price.put(360,20680)
            price.put(390,20570)
            price.put(420,20510)
            price.put(450,20450)
            price.put(480,20370)
            price.put(510,20270)
            price.put(540,20290)
            price.put(570,20260)
            price.put(600,20250)
            price.put(630,20190)
        }
        else if(binding.code22.isChecked) {
            price.put(30,33280)
            price.put(60,30870)
            price.put(90,29380)
            price.put(120,28260)
            price.put(150,27970)
            price.put(180,25130)
            price.put(210,24680)
            price.put(240,24490)
            price.put(270,24310)
            price.put(300,24340)
            price.put(330,24060)
            price.put(360,23940)
            price.put(390,23850)
            price.put(420,23810)
            price.put(450,23750)
            price.put(480,23680)
            price.put(510,23590)
            price.put(540,23610)
            price.put(570,23600)
            price.put(600,23590)
            price.put(630,23550)
        }
        else if(binding.code23.isChecked) {
            price.put(30,34750)
            price.put(60,32340)
            price.put(90,30850)
            price.put(120,29730)
            price.put(150,29440)
            price.put(180,26600)
            price.put(210,26150)
            price.put(240,25960)
            price.put(270,25780)
            price.put(300,25810)
            price.put(330,25530)
            price.put(360,25410)
            price.put(390,25320)
            price.put(420,25280)
            price.put(450,25220)
            price.put(480,25150)
            price.put(510,25060)
            price.put(540,25080)
            price.put(570,25070)
            price.put(600,25060)
            price.put(630,25020)
        }



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

        System.out.println("\n--------------------------------------------\n아래 부 면당단가")

        val a:Int = price.get(Bu-Bu%30)?: 0
        System.out.println(a)
        val m:Int = count.get(Bu-Bu%30)?:0
        System.out.println(m)
        val b:Double = floor((a.toDouble() / (m+8).toDouble())*100)/100
        System.out.println(b)

        System.out.println("\n--------------------------------------------\n윗 부 면당단가")
        val a2:Int = price.get(Bu+(30-(Bu%30)))?: 0
        System.out.println(a2)
        val m2:Int = count.get(Bu+(30-(Bu%30)))?: 0
        System.out.println(m2)
        val b2:Double = floor((a2.toDouble() / (m2+8).toDouble())*100)/100
        System.out.println(b2)

        System.out.println("\n--------------------------------------------\n요청 부 면당단가")

        val c:Double = floor((b2+((b-b2)/30*((Bu+(30-(Bu%30)))-Bu)))*100)/100
        System.out.println(c)
        val d:Double = c * (Meon+8)
        System.out.println(d)

        System.out.println("\n--------------------------------------------\n최종가격")

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

        if (binding.normalPU2.isChecked || binding.normalPU3.isChecked) {
            final += 2630
        }
        else if (binding.royalPU2.isChecked || binding.royalPU3.isChecked) {
            final += 2790
        }
        else if (binding.Woo2.isChecked || binding.Woo3.isChecked){
            final += 2470
        }
        else if (binding.color2.isChecked || binding.color3.isChecked) {
            final += 2330
        }
        else if (binding.fourCross2.isChecked || binding.fourCross3.isChecked) {
            final += 2320
        }

        val degeumA = final * Bu
        System.out.println(degeumA)
        val degeumB = degeumA + (degeumA * 0.0054)
        System.out.println(degeumB)
        val degeumC = degeumB / Bu
        System.out.println(degeumC)
        val degeumD = degeumC - degeumC%10
        System.out.println(degeumD)







        return degeumD.toString()
    }
}