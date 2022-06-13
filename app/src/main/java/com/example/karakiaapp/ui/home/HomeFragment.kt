package com.example.karakiaapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.karakiaapp.R
import com.example.karakiaapp.data.KarakiaItem
import com.example.karakiaapp.data.KarakiaItemAdapter
import com.example.karakiaapp.data.StaticValue
import com.example.karakiaapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val exampleList = generateDummyList(5)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        if (!StaticValue.isAccept) {
            val dView = layoutInflater.inflate(R.layout.dialog_disclaimer, null)
            val tvAccept = dView.findViewById<TextView>(R.id.tv_accept)
            val dialog = AlertDialog.Builder(context).setCancelable(false).setView(dView).show()
            tvAccept.setOnClickListener {
                StaticValue.isAccept = true
                dialog.dismiss()
            }
        }

        home_RV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = KarakiaItemAdapter(exampleList)
        }
    }

    private fun generateDummyList(size: Int): ArrayList<KarakiaItem> {

        val karakiStringList = ArrayList<KarakiaItem>()
        karakiStringList.add(
            KarakiaItem(
                1,
                "Karakia Timatanga",
                "Opening",
                R.drawable.image1,
                "Description 1",
                "Tōia mai ngā tauira\n" +
                        "Tōia mai ngā kaimahi\n" +
                        "Tōia mai ngā tāngata nō ngā hau e whā\n" +
                        "Tōia mai i runga i te aroha me te\n" +
                        "rangimārie\n" +
                        "Kia mau, kia ita\n" +
                        "Haumi e, hui e\n" +
                        "Taiki e!\n",
                "Draw forward our learners\n" +
                        "Draw forward our staff\n" +
                        "Draw forward the many people from the\n" +
                        "four corners of the world\n" +
                        "Draw them forward under the mantle of\n" +
                        "love and peace\n" +
                        "Let us remain steadfast to these words",
                R.raw.karakia1,
                "0.22"
            )
        )
        karakiStringList.add(
            KarakiaItem(
                2,
                "Karakia Timatanga",
                "Opening 2",
                R.drawable.image2,
                "Description 2",
                "Whakataka te hau ki te uru,\n" +
                        "Whakataka te hau ki te tonga.\n" +
                        "Kia makinakina ki uta,\n" +
                        "Kia mataratara ki tai.\n" +
                        "E hi ake ana te atakura\n" +
                        "he tio, he huka, he hauhunga.\n" +
                        "Haumi e! Hui e! Taiki e!",
                "The wind swings to the west\n" +
                        "then turns into a southerly.\n" +
                        "making it prickly cold inland,\n" +
                        "and piercingly cold on the coast.\n" +
                        "May the dawn rise red-tipped\n" +
                        "on ice, on snow, on frost.\n" +
                        "Join! Gather! Intertwine!",
                R.raw.karakia1,
                "0.24"
            )
        )
        karakiStringList.add(
            KarakiaItem(
                3,
                "Karakia ki te kai",
                "Blessing for food",
                R.drawable.image3,
                "Description 3",
                "E te Atua\n" +
                        "Whakapainga ēnei kai\n" +
                        "Hei oranga mō ō mātou tinana\n" +
                        "Whāngaia hoki ō mātou wairua ki te taro\n" +
                        "o te ora\n" +
                        "Ko Ihu Karaiti tō mātou Ariki\n" +
                        "Ake, ake, ake\n" +
                        "Amine",
                "Lord God\n" +
                        "Bless this food\n" +
                        "For the goodness of our bodies\n" +
                        "Feeding our spiritual needs also with\n" +
                        "the bread of life\n" +
                        "Jesus Christ, our Lord\n" +
                        "For ever and ever\n" +
                        "Amen\n",
                R.raw.karakia1,
                "0.27"
            )
        )
        karakiStringList.add(
            KarakiaItem(
                4,
                "Karakia Whakamutunga",
                "Closing",
                R.drawable.image4,
                "Description 4",
                "Kia tau ki ā tātou katoa\n" +
                        "Te atawhai o tō tātou Ariki ā Ihu Karaiti\n" +
                        "Me te aroha o te Atua\n" +
                        "Me te whiwhinga tahitanga\n" +
                        "Ki te Wairua Tapu\n" +
                        "Āke, ake, ake\n" +
                        "Āmine\n",
                "May the grace of the Lord Jesus Christ\n" +
                        "And the love of God and the fellowship\n" +
                        "of the Holy Spirit Be with you all\n" +
                        "forever and ever,\n" +
                        "Amen",
                R.raw.karakia1,
                "0.22"
            )
        )
        karakiStringList.add(
            KarakiaItem(
                5,
                "Karakia Whakamutunga",
                "Closing 2",
                R.drawable.image5,
                "Description 5",
                "He hōnore, he korōria\n" +
                        "Maungārongo ki te whenua.\n" +
                        "Whakaaro pai e\n" +
                        "Kingā tangata katoa\n" +
                        "Ake, ake, ake.\n" +
                        "Āmine\n",
                "Honour, glory and\n" +
                        "peace to the land.\n" +
                        "May good thoughts come\n" +
                        "to all men\n" +
                        "for ever and ever.\n" +
                        "Amen.",
                R.raw.karakia1,
                "0.16"
            )
        )
        return karakiStringList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}