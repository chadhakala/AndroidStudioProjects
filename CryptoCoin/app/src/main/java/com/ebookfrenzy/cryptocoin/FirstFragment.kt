package com.ebookfrenzy.cryptocoin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ebookfrenzy.cryptocoin.databinding.FragmentFirstBinding
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var coins: ArrayList<Coin> = ArrayList()
    private var names: ArrayList<String> = ArrayList()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    fun printCatData() {
        val api = "dd5949bde5962879f0cce38d46b93483f30ffdcf405cf74c865a9ddc1d66705a"
        val catUrl = "https://rest.coincap.io/v3/assets?apiKey=" + api
        val queue = Volley.newRequestQueue(this.context)
// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            { response ->
                val obj = JSONObject(response)
                val catsArray = obj.getJSONArray("data")
//indices from 0 through catsArray.length()-1
                for(i in 0 until catsArray.length()) {
//${} is to interpolate the string /
// uses a string template
                    val theCat : JSONObject = catsArray.getJSONObject(i)
//now get the properties we want: name and description
                        val name = theCat.getString("name")
                        val symbol = theCat.getString("symbol")
                        val supply = theCat.getDouble("supply").toInt()
                        val price = theCat.getDouble("priceUsd")
                        val change = theCat.optDouble("changePercent24Hr", 0.0)
                        val coin = Coin(name, symbol, supply, price, change)
                        coins.add(coin)
                        names.add(name)

                }//end for
                val adapter : ArrayAdapter<String> = ArrayAdapter(this.requireContext(), android.R.layout.simple_selectable_list_item, names)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
                binding.spinner.onItemSelectedListener = object:
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
/*                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,
                            bundleOf("POSITION" to position))*/
                        displayInfo(position)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
            },
            {
                Log.i("MainActivity", "That didn't work!")
            })
// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }//end printCatData
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printCatData()
    }
    fun displayInfo(position : Int) {
        binding.name.text = coins[position].name
        binding.symbol.text = "Symbol: ${coins[position].symbol}"
        binding.supply.text = "Supply: ${coins[position].supply}"
        binding.price.text = "Price: ${"$%.2f".format(coins[position].price)}"
        binding.change.text = "change: ${"$%.2f".format(coins[position].change)}"

    }
}